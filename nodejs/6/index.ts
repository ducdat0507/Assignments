import { configDotenv } from "dotenv";
import { setLogger } from "./logging/Logger";
import { ConsoleLogger } from "./logging/ConsoleLogger";
import { MySQLRepositoryHolder } from "./drivers/databases/mysql/repositories/RepositoryHolder";
import { createPool } from "mysql2";
import { ControllerHolder } from "./adapters/controllers/ControllerHolder";
import { ExpressBackend, setupBackend } from "./drivers/backends/express";

configDotenv();

// Init logger
setLogger(new ConsoleLogger());

// Init database
const db = new MySQLRepositoryHolder({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
})

// Init backend
const controllers = new ControllerHolder(db);
const backend = new ExpressBackend();
backend.setup(controllers);
