import path from 'path';
import express from 'express';
import { createConnection } from 'mysql2';
import dotenv from 'dotenv';

dotenv.config();
const app = express();
const port = +process.env.APP_PORT;

const db = createConnection({
    host: process.env.DB_HOST,
    port: process.env.DB_HOST,
    database: process.env.DB_NAME,
    user: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
})
db.connect();

db.query("CREATE TABLE IF NOT EXISTS Car (Id int AUTO_INCREMENT PRIMARY KEY, Name varchar(128) NOT NULL, Brand varchar(128) NOT NULL, Description varchar(2048) NOT NULL, Price decimal(20,8), Quantity int)");

app.post('/api/car', express.json(), (req, res) => {
    const { name, brand, description, price, quantity } = req.body ?? {};
    db.query("INSERT INTO Car (Name, Brand, Description, Price, Quantity) VALUES (?)", [
        [name, brand, description, price, quantity]
    ], (err, result) => {
        if (err) {
            console.error(new Date(), err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            console.log(new Date(), req.body, result);
            res.json({ ok: 1, id: result.insertId });
        }
    })
})

app.get('/api/car', express.json(), (req, res) => {
    db.query("SELECT Name, Brand, Description, Price, Quantity FROM Car", [
    ], (err, result) => {
        if (err) {
            console.error(new Date(), err);
            res.status(500).json({ error: "Internal Error" });
        } else {
            console.log(new Date(), req.body, result);
            res.json({ ok: 1, results: result });
        }
    })
})

app.get('/api/car/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.query("SELECT Name, Brand, Description, Price, Quantity FROM Car WHERE Id = ?", [
        id
    ], (err, result) => {
        if (err) {
            console.error(new Date(), err);
            res.status(500).json({ error: "Internal Error" });
        } else if (!result[0]) {
            console.log(new Date(), req.body, result);
            res.status(404).json({ error: "No such car with id " + id + " exists" });
        } else {
            console.log(new Date(), req.body, result);
            res.json({ ok: 1, results: result });
        }
    })
})

app.put('/api/car/:id', express.json(), (req, res) => {
    const { id } = req.params;
    const { name, brand, description, price, quantity } = req.body ?? {};
    db.query("UPDATE Car SET Name = ?, Brand = ?, Description = ?, Price = ?, Quantity = ? WHERE Id = ?", [
        name, brand, description, price, quantity, id
    ], (err, result) => {
        if (err) {
            console.error(new Date(), err);
            res.status(500).json({ error: "Internal Error" });
        } else if (!result.affectedRows) {
            console.log(new Date(), req.body, result);
            res.status(404).json({ error: "No such car with id " + id + " exists" });
        } else {
            console.log(new Date(), req.body, result);
            res.json({ ok: 1 });
        }
    })
})

app.delete('/api/car/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.query("DELETE FROM Car WHERE Id = ?", [
        id
    ], (err, result) => {
        if (err) {
            console.error(new Date(), err);
            res.status(500).json({ error: "Internal Error" });
        } else if (!result.affectedRows) {
            console.log(new Date(), req.body, result);
            res.status(404).json({ error: "No such car with id " + id + " exists" });
        } else {
            console.log(new Date(), req.body, result);
            res.json({ ok: 1, items: result.affectedRows });
        }
    })
})



app.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}`);
})
