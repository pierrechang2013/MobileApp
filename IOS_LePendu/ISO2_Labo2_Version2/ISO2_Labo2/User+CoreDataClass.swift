//
//  User+CoreDataClass.swift
//  ISO2_Labo2
//
//  Created by Liang Chang (Étudiant) on 2022-05-17.
//
//

import Foundation
import CoreData

@objc(User)
public class User: NSManagedObject {
    
    // 获取context
    func getContext() -> NSManagedObjectContext {
        let mDelegate = UIApplication.shared.delegate as! AppDelegate
        return mDelegate.persistentContainer.viewContext
    }
    
    
    // MARK:增
    func addData(user: User) {
        //获取全局AppDelegate并且获得托管上下文
        let mDelegate = UIApplication.shared.delegate as! AppDelegate
        let mContext = mDelegate.persistentContainer.viewContext
        //因为后面要设置属性，所以需要强制转换为 as! XXX(生成的，与实体对应的对象)
        let newUser = NSEntityDescription.insertNewObject(forEntityName: "User", into: mContext)as! User
        
        newUser.name = user.name
        
        mDelegate.saveContext()
        
    }
    
    func findData() -> [AnyObject]?{
        
        //获取全局AppDelegate并且获得托管上下文
        let mDelegate = UIApplication.shared.delegate as! AppDelegate
        let mContext = mDelegate.persistentContainer.viewContext
        let entity:NSEntityDescription? = NSEntityDescription.entity(forEntityName: "User", in: mContext)
        
        let request = NSFetchRequest<User>(entityName: "User")
        request.fetchOffset
        //跳过的数据量，主要是多页查询用的
        request.fetchLimit = 5
        request.entity = entity
        
        //设置约束条件
        //            let predicate = NSPredicate(format: "name='xiaohua'", "")
        //            request.predicate = predicate
        
        //因为查询结果可能为空，所以动docatch捕获可能的异常
        do {
            let results:[AnyObject]? = try managedObjectContext.fetch(request)
            for u : User in results as! [User] {
                print("findOut:\(u.name!)")
            }
        } catch {
            print("didNotFind.")
        }
        
        return results
        
    }
    
    func deleteDataAsCondition(users:[User]) {
        // 获取上下文
        let mDelegate = UIApplication.shared.delegate as! AppDelegate
        let mContext = mDelegate.persistentContainer.viewContext
        
        let entity = NSEntityDescription.entity(forEntityName: "User", in: mContext)
        
        // 请求实体（Fetch：取、拿来）
        let request = NSFetchRequest<User>()
        request.entity = entity
        
        do {
            let results:[AnyObject]? = try mContext.fetch(request)
            
            for user in results as![User] {
                if user.name == "test1" {
                    mContext.delete(user)
                    mDelegate.saveContext()
                }
            }
        } catch  {
            print("删除数据失败")
        }
        
    }
    
    // MARK:删
    //    func deleteAllData() {
    
    //
    //        // 获取上下文
    //        let mDelegate = UIApplication.shared.delegate as! AppDelegate
    //        let mContext = mDelegate.persistentContainer.viewContext
    
    //        NSFetchRequest request = [[NSFetchRequest alloc] initWithEntityName:@"User"];
    //2.建立删除请求 参数是：查询请求
    //NSBatchDeleteRequest是iOS9以后新增的API，不兼容iOS8及之前的系统
    //        NSBatchDeleteRequest deletRequest = [[NSBatchDeleteRequest alloc] initWithFetchRequest:request];
    //3.使用存储调度器(NSPersistentStoreCoordinator)执行删除请求
    /** Request：存储器请求（NSPersistentStoreRequest） 删除请求NSBatchDeleteRequest继承于NSPersistentStoreRequest context：管理对象上下文 */
    //        [self.persistentStoreCoordinator executeRequest:deletRequest withContext:self.managedObjectContext error:nil];
    //
    //
    //    }
    
    
    // MARK: - 【改】的功能
    func coreDataUpdate(_ point: Int) {
        
        // 获取上下文
        let mDelegate = UIApplication.shared.delegate as! AppDelegate
        let mContext = mDelegate.persistentContainer.viewContext
        
        // 设置查询的实体
        let entity = NSEntityDescription.entity(forEntityName: "User", in: mContext)
        let request = NSFetchRequest<User>(entityName: "User")
        //request.fetchOffset = 0
        //request.fetchLimit = 10
        request.entity = entity
        
        
        do {
            let results:[AnyObject]? = try mContext.fetch(request)
            
            for u in results as![User] {
                if u.point == 100 {
                    u.point = point
                    mDelegate.saveContext()
                }
            }
        } catch  {
            print("修改数据失败")
        }
        
    }
}

// MARK: - 【查】的功能
/*func coreDataSearch(command: String) {
 switch command {
 case "测试读取":
 // 获取上下文
 let mContext = getContext()
 
 // 设置查询的实体，此处的实体是MyTest
 let entity = NSEntityDescription.entity(forEntityName: "MyTest", in: mContext)
 
 // 请求实体（Fetch：取、拿来）
 let request = NSFetchRequest<MyTest>()
 request.entity = entity
 
 do {
 let results:[AnyObject]? = try mContext.fetch(request)
 
 for myTest in results as![MyTest] {
 print("value=\(myTest.testSaveInt)")
 }
 } catch  {
 print("读取数据失败")
 }
 
 default:
 break
 }
 }*/

