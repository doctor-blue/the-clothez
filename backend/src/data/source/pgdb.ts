import Pg from 'pg';
const Pool = Pg.Pool;


export const pool = new Pool({
    user: process.env.DB_USER,
    host: process.env.DB_HOST,
    database: 'theclothez',
    password: process.env.DB_PWD,
    port: Number(process.env.DB_PORT)
});
