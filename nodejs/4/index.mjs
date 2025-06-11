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

app.post('/api/product', express.json(), (req, res) => {
    const { name, description, price, quantity } = req.body;
    db.query("INSERT INTO Product (Name, Description, Price, Quanity) VALUES ?", [
        [name, description, price, quantity]
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1, id: result.insertId[0] });
        }
    })
})

app.get('/api/product', express.json(), (req, res) => {
    db.query("SELECT (Name, Description, Price, Quanity) FROM Product", [
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1, results: result });
        }
    })
})

app.get('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.query("SELECT (Name, Description, Price, Quanity) FROM Product WHERE Id = ?", [
        id
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1, results: result });
        }
    })
})

app.put('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    const { name, description, price, quantity } = req.body;
    db.query("UPDATE Product SET Name = ?, Description = ?, Price = ?, Quanity = ? WHERE Id = ?", [
        name, description, price, quantity, id
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1 });
        }
    })
})

app.delete('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.query("DELETE FROM Product WHERE Id = ?", [
        id
    ], (err, result) => {
        if (err) {
            console.log(err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            res.json({ ok: 1, items: result.affectedRows });
        }
    })
})



app.listen(port, () => {
    console.log(`Server is listening on http://localhost/${port}`);
})
