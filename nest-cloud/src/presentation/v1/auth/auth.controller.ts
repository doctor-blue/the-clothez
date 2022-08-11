import { Body, Controller, Get, HttpStatus, Post, Req, Res, UseFilters, UseGuards } from "@nestjs/common";
import { Response } from "express";
import { SUCCESS_STATUS } from "src/domain/const/StatusConst";
import { HttpExceptionFilter } from "src/presentation/exception.filter";
import { LoginDto } from "src/presentation/request/LoginDto";
import { IResponse } from "src/presentation/response/IResponse";
import { v1Path } from "../path";
import { AuthService } from "./auth.service";
import { JwtAuthGuard } from "./local.authguard";


@Controller(v1Path + "auth")
export class AuthController {
    constructor(private readonly authService: AuthService) { }
    
    // @UseGuards(LocalAuthGuard)
    @Post("login")
    @UseFilters(HttpExceptionFilter)
    async login(
        @Body() loginDto: LoginDto,
    ) {      
      return await this.authService.login(loginDto.userName, loginDto.password)
    }

    @UseGuards(JwtAuthGuard)
    @Get("profile")
    @UseFilters(HttpExceptionFilter)
    async profile(){
      console.log("Profile");
      
      return "Hello"
    }
}