import path from 'path';
import express from 'express';
import { createConnection } from 'mysql2';
import dotenv from 'dotenv';

const app = express();
const port = process.env.APP_PORT;
dotenv.config();

const db = createConnection({
    host: process.env.DB_HOST,
    port: process.env.DB_HOST,
    database: process.env.DB_NAME,
    user: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
})
db.connect();

app.get('/student', (req, res) => {
    console.log(req);
    return res.sendFile("calc.html", { root: path.dirname("") });
});

app.post('/api/student', express.json(), (req, res) => {
    const { name, birthday, grade } = req.body;
    db.query("INSERT INTO Students (Name, Birthday, Grade) VALUES ?", [
        [name, birthday, grade]
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1, id: result.insertId[0] });
        }
    })
})

app.listen(port, () => {
    console.log(`Server is listening on http://localhost/${port}`);
})
