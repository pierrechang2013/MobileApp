//
//  main.swift
//  Labo2_Etap1
//
//  Created by Liang Chang (Étudiant) on 2022-03-11.
//

import Foundation
var str = "Bienvenue au jeu!"
let borneMin = 1
let borneMax = 100
let chiffreMystère = Int(arc4random_uniform(10) + 1) // from 1-10 random number
//permet de calculer un chiffre entre borneMin et borneMax
let nbCoupsMax = 3
print("DB 1: \(chiffreMystère)")

for i in borneMin...borneMax {
    print("Entrez un nombre:")
    let res:String? = readLine()
    let nb = Int(res!) // HMMMM Ce truc marche pas. Que faire?
    print("DB 2: \(nb)")
    if i > nbCoupsMax {
        print("Perdu")
        break // Faut-il mettre un break ici?
    } else if nb == chiffreMystère {
        print("Gagné")
        break
    } else {
        if nb! < chiffreMystère {
            print("Le nombre à deviner est plus grand.")
        } else {
            print("Le nombre à deviner est plus petit.")
        }
    }
    
}
print("Bye")

