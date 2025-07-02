import path from 'path';
import express from 'express';
import dotenv from 'dotenv';
import { MongoClient } from 'mongodb';

dotenv.config();
const app = express();
const port = +process.env.APP_PORT;

const client = new MongoClient(process.env.DB_URL)
await client.connect();
const db = client.db();

app.post('/api/product', express.json(), (req, res) => {
    const { name, description, price } = req.body ?? {};
    db.collection("products").insertOne({
        name, description, price
    }).then(result => {
        if (result.acknowledged) {
            res.status(200).json({ id: result.insertedId });
        } else {
            res.status(500).json({ error: "Internal Error" });
        }
    })
})

app.get('/api/product', express.json(), (req, res) => {
    db.collection("products").find().toArray().then(products => {
        res.status(200).json(products);
    });
})

app.get('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.collection("products").findOne({_id: id}).then(product => {
        if (product) res.status(200).json(product);
        else res.status(404).json({ error: "Product not found" });
    });
})

app.put('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    const { name, description, price } = req.body ?? {};
    db.collection("products").updateOne({_id: id}, {name, description, price}).then(result => {
        if (result.modifiedCount) res.status(200).json({ok: 1});
        else res.status(404).json({ error: "Product not found" });
    });
})

app.delete('/api/product/:id', express.json(), (req, res) => {
    const { id } = req.params;
    db.collection("products").deleteOne({_id: id}).then(result => {
        if (result.deletedCount) res.status(200).json({ok: 1});
        else res.status(404).json({ error: "Product not found" });
    });
})



app.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}`);
})
