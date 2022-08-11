import { ForbiddenException, HttpException, Injectable, UnauthorizedException } from '@nestjs/common';
import RepositoryModule from 'src/di/RepositoryModule';
import { Status } from 'src/domain/model/Status';
import { Token } from 'src/domain/model/Token';
import User from 'src/domain/model/User';
import { Repository } from 'typeorm';
import StateCallback from 'src/domain/utils/StateCallback';
import { IResponse } from 'src/presentation/response/IResponse';
import { UserEntity } from 'src/data/entity/UserEntity';
import { InjectRepository } from '@nestjs/typeorm';
import * as bcrypt from 'bcrypt';
import { INCORRECT_USER_NAME_PWD } from 'src/domain/const/ErrorConst';
import { JwtService } from '@nestjs/jwt';


@Injectable()
export class AuthService {

    constructor(
        @InjectRepository(UserEntity)
        private readonly userRepository: Repository<UserEntity>,
        private readonly jwtService: JwtService
    ) { }

    async login(userName: string, password: string) {

        const user = await this.validateUser(userName, password);
        const payload = { user_name: user.email, user_id: user.user_id };

        return new Token(this.jwtService.sign(payload), "");
    }

    async validateUser(userName: string, password: string): Promise<UserEntity> {
        const user = await this.userRepository.findOneBy({ user_name: userName });
        if (!user) {
            throw new UnauthorizedException(INCORRECT_USER_NAME_PWD.toJson())
        }

        const validPassword = await bcrypt.compare(password, user.password);
        if (!validPassword) {
            throw new UnauthorizedException(INCORRECT_USER_NAME_PWD.toJson())
        }
        return user
    }

    signUp(user: User): IResponse<boolean> {
        return new IResponse(null, new Status(123, ""))
    }

    refreshToken(refreshToken: string): IResponse<Token> {
        return new IResponse(null, new Status(123, ""))
    }
}
