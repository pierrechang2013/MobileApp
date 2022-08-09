import { injectable } from 'inversify';
import { Collection, MongoClient } from 'mongodb';
import { UserDTO } from '../../../common/user';
import { User } from '../interfaces';

const url = 'mongodb://127.0.0.1:27017';


@injectable()
/*
* Cette classe s'occupe des communications avec MongoDB
*/
export class MongodbService {

    private _client: MongoClient = new MongoClient(url);
    private _collection: Collection<User>;
    
    constructor(){
        //Pourrait causer des problèmes en production
        this._client.connect();
        //Collection à utiliser
        this._collection = this._client.db('tp2').collection<User>('users');
    }
    
    //Retourne les informations d'un utilisateur à partir de son username
    async getUserByUsername(username: string):Promise<User | null>{
        
        //TODO Trouver l'utilisateur en fonction de son nom d'utilisateur
        const user = await this._collection.findOne<User>({
            'username': username});
        //TODO Retourner l'utilisateur avec son _id
        
        return user;
        //throw new Error('Not implemented method');
    }
    
    //Fait la création d'un utilisateur dans la base de données
    //async createUser(username: string, hash: string): Promise<User | null>{
        async createUser(username: string, hash: string): Promise<User | null>{
        
        //TODO Créer un utilisateur en fonction des information d'authentification
        //Utilisez l'interface Use
        
        const user:User={
            username: username,
            hash:hash
        }; 

       const id = await (await this._collection.insertOne(user)).insertedId;

       const user1 = await this._collection.findOne({ '_id': id });


       //throw new Error('Not implemented method');  

           
       
        return user1;
       
        //TODO Retourner le user créé avec son _id

        
    }

}