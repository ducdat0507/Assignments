let stompClient = null
let username = document.getElementById('username').value
const usersList = document.getElementById('users')
const messagesDiv = document.getElementById('messages')
const typingDiv = document.getElementById('typing')
function connect() {
    const socket = new SockJS('/ws')
    stompClient = Stomp.over(socket)
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/public', function (msg) {
            const body = JSON.parse(msg.body)
            showMessage(body)
            if (body.type === 'JOIN' || body.type === 'LEAVE') {
                refreshUsers()
            }
        })
        stompClient.subscribe('/topic/users', function (msg) {
            const list = JSON.parse(msg.body)
            renderUsers(list)
        })
        stompClient.subscribe('/topic/typing', function (msg) {
            const body = JSON.parse(msg.body)
            if (body.type === 'TYPING') {
                typingDiv.innerText = body.sender + ' is typing...'
            } else {
                typingDiv.innerText = ''
            }
        })
        stompClient.subscribe('/user/queue/private', function (msg) {
            const body = JSON.parse(msg.body)
            showMessage(body)
        })
        stompClient.send('/app/chat.addUser', {}, JSON.stringify({sender: username}))
    })
}
function showMessage(message) {
    const el = document.createElement('div')
    if (message.type === 'JOIN') {
        el.innerText = message.sender + ' has joined the chat'
        el.className = 'sys'
    } else if (message.type === 'LEAVE') {
        el.innerText = message.sender + ' has left the chat'
        el.className = 'sys'
    } else if (message.type === 'PRIVATE') {
        el.innerText = '(Private) ' + message.sender + ': ' + message.content
        el.className = 'private'
    } else {
        el.innerText = message.sender + ': ' + message.content
    }
    messagesDiv.appendChild(el)
    messagesDiv.scrollTop = messagesDiv.scrollHeight
}
function renderUsers(list) {
    usersList.innerHTML = ''
    list.forEach(u => {
        const li = document.createElement('li')
        li.innerText = u
        li.onclick = () => startPrivate(u)
        usersList.appendChild(li)
    })
}
function refreshUsers() {
    // request of users is via topic/users updates
}
function startPrivate(target) {
    const text = prompt('Private message to ' + target)
    if (!text) return
    const msg = {type: 'PRIVATE', sender: username, recipient: target, content: text}
    stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(msg))
}
document.getElementById('send').addEventListener('click', function () {
    const input = document.getElementById('message')
    const text = input.value.trim()
    if (!text) return
    const msg = {type: 'CHAT', sender: username, content: text}
    stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(msg))
    input.value = ''
})
let typingTimer = null
const inputEl = document.getElementById('message')
inputEl.addEventListener('input', function () {
    if (!stompClient) return
    stompClient.send('/app/chat.typing', {}, JSON.stringify({type: 'TYPING', sender: username}))
    clearTimeout(typingTimer)
    typingTimer = setTimeout(() => {
        stompClient.send('/app/chat.typing', {}, JSON.stringify({type: 'STOP_TYPING', sender: username}))
    }, 1000)
})
connect()
