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

app.post('/media/video.mp4', (req, res) => {

})

app.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}`);
})
