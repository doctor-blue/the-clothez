import { Injectable, UnauthorizedException } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { AUTHENTICATION_FAILURE } from 'src/domain/const/ErrorConst';

@Injectable()
export class JwtAuthGuard extends AuthGuard('jwt') {

    handleRequest(err, user: any, info) {
        // You can throw an exception based on either "info" or "err" arguments
        if (err || !user) {
            throw new UnauthorizedException(AUTHENTICATION_FAILURE.toJson());
        }
        return user;
    }
}