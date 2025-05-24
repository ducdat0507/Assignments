
import express from 'express';
const app = express();
const port = 3000;

app.post('/calc', express.json(), (req, res) => {
    const {a, b, op} = req.body;
    if (typeof a != "number") return res.status(400).json({ "error": "`a` must be a number" });
    if (typeof b != "number") return res.status(400).json({ "error": "`b` must be a number" });
    switch (op) {
        case "add": case "plus":
            return res.json({ "result": a + b });
        case "sub": case "subtract": case "minus":
            return res.json({ "result": a - b });
        case "mul": case "multiply": case "times":
            return res.json({ "result": a * b });
        case "div": case "divide":
            return res.json({ "result": a / b });
        default: 
            return res.status(400).json({ "error": "Not a valid operator" });
    }
})

app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
})
