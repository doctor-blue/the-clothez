export { };

declare global {
  namespace NodeJS {
    interface ProcessEnv {
      DB_PORT: number;
      DB_USER: string;
      DB_PWD: string;
      DB_HOST: string;
      ACCESS_TOKEN_SECRET: string;
      REFRESH_TOKEN_SECRET: string;
      ENV: 'test' | 'dev' | 'prod';
    }
  }
}