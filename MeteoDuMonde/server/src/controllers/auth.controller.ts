import { JsonObject } from './../../../../tp1/tp1 zhengshi/src/classes';
import { UserDTO } from './../../../common/user';
import { Router, Request, Response } from 'express';
import { inject, injectable } from 'inversify';
import { Authentification } from '../../../common/authentification';
import { User } from '../interfaces';
import { AuthService } from '../services/auth.service';
import { MongodbService } from '../services/mongodb.service';
import { TYPES } from '../types';


@injectable()
export class AuthController {
    public constructor(@inject(TYPES.AuthService) private _authService: AuthService,
        @inject(TYPES.MongodbService) private _mongodbService: MongodbService) {
        //empty
    }

    public get router(): Router {

        const router: Router = Router();

        // -> /api/v1/auth/loggin
        router.post('/login', async (req: Request, res: Response) => {//登陆
            const auth: Authentification = req.body;

            const username = auth.username;//用户名
            const password = auth.password;//密码

            this._mongodbService.getUserByUsername(username).then(user => {

                if (user===null||user===undefined){//没有这个用户名的对象
                    res.status(403).redirect('/api/v1/auth');//重新登录

                }else{//有这个用户名
                    const userId = user?._id;
                    console.log('获得用户的id是:' + userId);

                    let pwdHash = user?.hash;

                    if (pwdHash===undefined){
                        pwdHash = '';
                    }
                    this._authService.isPasswordValid(password,pwdHash).then(bool=>{
                        if(bool===false){
                            console.log('验证失败');
                            res.status(403).json();
                        }else{
                            
                            console.log('验证成功');
                            const token = this._authService.generateToken(userId);
                            const userDTO:UserDTO={

                                _id: userId,
                                
                                token: token,
   
                                username: username,

                            };

                            res.status(200).json(userDTO);

                            console.log('发送');



                        }
                    });

                }
                
            
            
            });


            //TODO Trouver l'utilisateur dans la BD, si l'utilisateur est null retournez le code 403

            //TODO Comparer le mot de passe de la BD avec le mot de passe de la requête, utiliser le auth.service
            //Retournez le code 403 au besoin

            //TODO Générer le jeton de l'utilisateur à l'aide du service auth.service et assigner à l'utilisateur

            //TODO Retourner les informations de l'utilisateur sans le hash (voir interface UserDTO) 

        });

        // -> /api/v1/auth/signup
        router.post('/signup', async (req: Request, res: Response) => {//注册
            const auth: Authentification = req.body;//
            const username = auth.username;
            const pws = auth.password;
            const user = this._mongodbService.getUserByUsername(username);
            
            user.then(data=>{

                console.log('data?._id:' + data?._id);

                if (data?._id == undefined || data?._id === null){
                    
                    //let hashPwd ='';

                    this._authService.encryptPassword(pws).then(data => {
                    
                    //data; 
                    
                    console.log('data:' + data);
                    
                    //console.log('hashPwd:' + hashPwd);

                        const newUser = this._mongodbService.createUser(username, data);

                    //let id = '';

                    newUser.then(data=> {

                    const toke = this._authService.generateToken(data?._id);

                    const user: UserDTO={
                        _id: data?._id,
                        token : toke,
                        username : username

                    };

                    res.status(200).json(user);
                    });
                    //res.redirect('/api/v1/auth');
                    });

                }else{
                    res.status(403).redirect('/api/v1/auth');//重新登录
                }
            
            });
            
            //TODO Valider que l'utilisateur (username) n'est pas déjà dans la BD
            //Retounez un code 405 si déjà présent

            //TODO Chiffrer le mot de passe avec auth.service

            //TODO Ajouter l'utilisateur à la BD
            //Retounez un code 500 en cas de problème

            //TODO Générer le jeton de l'utilisateur à l'aide du service auth.service

            //TODO Retourner les informations de l'utilisateur sans le hash (voir interface UserDTO) 

        });

        return router;
    }

}