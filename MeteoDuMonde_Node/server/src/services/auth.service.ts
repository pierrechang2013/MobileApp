// eslint-disable-next-line @typescript-eslint/no-var-requires
import { injectable } from 'inversify';
import { environment } from '../env';
import * as jwt from 'jsonwebtoken';
import * as bcrypt from 'bcrypt'; 


@injectable()
/*
* Cette classe fait la gestion de l'authentification
*/
export class AuthService {
    constructor(){
        //empty
    }
    
    //Génère un jwt contenant l'id de l'utilisateur
    generateToken(userId: string): string{//使用id生成一个token
        //On fait la génération d'un jeton unique
        //{ _id: userId }是一个json对象或者是一个可以json化的buffer或字符串,environment.secret是秘钥
        return jwt.sign({ _id: userId }, environment.secret);//生成token  
    }
    
    //Retourne le id de l'utilisateur
    //token是需要被验证的字符串， environment.secret是秘钥
    decodeToken(token:string): string {//验证令牌
        const payload = jwt.verify(token, environment.secret);//验证token  
        if(typeof payload !== 'string'){
            return payload._id;
        }
        throw new Error('Invalid payload type');
    }
    
    //Fait le chiffrement du mot de passe
    async encryptPassword(password: string): Promise<string>{//对密码加密
        //On chiffre le mot de passe en effectuant 10 rounds
        return bcrypt.hash(password,10);//加密密码
    }
    
    //Fait la validation du mot de passe
    async isPasswordValid(password: string, hash: string): Promise<boolean>{//密码验证
        //On compare le mot de passe chiffré et non chiffré
        return await bcrypt.compare(password, hash);//密码验证
    }
}