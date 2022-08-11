import { Status } from "../model/Status";

export const INVALID_TOKEN = new Status(101, "Invalid token.");
export const EMAIL_ALREADY_EXISTS = new Status(102, "Email already exists.");
export const AUTHENTICATION_FAILURE = new Status(103, "Authentication failure.");
export const INCORRECT_USER_NAME_PWD = new Status(104, "Incorrect user name or password.");
export const EMAIL_DOES_NOT_CORRECT = new Status(105, "Email does not correct.")
export const UNKNOWN_ERR = new Status(106,"Unknown err");


