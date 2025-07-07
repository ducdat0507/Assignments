import express from "express";
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const port = 8081;
const filePath = path.join(__dirname, 'res/video.mp4');

app.get("/res/video.mp4", (req, res) => {
    try {
        const file = fs.statSync(filePath);
        const size = file.size;
        const range = req.range(size)
        console.log(range);
        if (range && range[0]) {
            const start = range[0].start;
            const end = range[0].end || size - 1;
            res.status(206).set({
                'Content-Type': 'video/mp4',
                'Content-Length': start - end + 1,
                'Content-Range': `bytes ${start}-${end}/${size}`,
                'Accept-Ranges': 'bytes',
            });
            fs.createReadStream(filePath, { start, end }).pipe(res);
        } else {
            res.status(200).set({
                'Content-Type': 'video/mp4',
                'Content-Length': size,
                'Accept-Ranges': 'bytes',
            });
            fs.createReadStream(filePath).pipe(res);
        }
    } catch (e) {
        console.log(e);
        res.status(500).send("Internal error");
    }
})

app.use("/", express.static(path.join(__dirname, 'res')));

app.listen(port, (e) => {
    if (!e) console.log(`App is listening on http://localhost:${port}/`)
})