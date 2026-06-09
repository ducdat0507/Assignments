<html>
<body>
<h1>New Exam</h1>
<form action="submitExam" method="post">
    <label>Name: <input type="text" name="name" required /></label><br/>
    <label>Duration (minutes): <input type="number" name="duration" required/></label><br/>
    <label>Description:<br/>
        <textarea name="description" rows="6" cols="60"></textarea>
    </label>
    <p>
        <button type="submit">Submit</button>
        <button type="button" onclick="location.href='exams'">Cancel</button>
    </p>
</form>
</body>
</html>
