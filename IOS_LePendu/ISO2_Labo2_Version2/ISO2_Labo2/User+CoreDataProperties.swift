//
//  User+CoreDataProperties.swift
//  ISO2_Labo2
//
//  Created by Liang Chang (Étudiant) on 2022-05-17.
//
//

import Foundation
import CoreData


extension User {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<User> {
        return NSFetchRequest<User>(entityName: "User")
    }

    @NSManaged public var name: String?
    @NSManaged public var points: Int16
    @NSManaged public var gameType: Int16

}

extension User : Identifiable {

}
