import Pg from 'pg';
const Pool = Pg.Pool;

export const pool = new Pool({
    user: 'postgres',
    host: 'localhost',
    database: 'clothez',
    password: '19801977',
    port: 5432
});
