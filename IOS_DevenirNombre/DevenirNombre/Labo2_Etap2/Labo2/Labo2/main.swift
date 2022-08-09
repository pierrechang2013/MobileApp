//
//  main.swift
//  Labo2
//
//  Created by Liang Chang (Étudiant) on 2022-03-11.
//  Pour bonus , 69 élements dans la librairie d’objets de Xcode
//

import Foundation

print("Bienvenue au jeu!")

var isContinue = true

while(isContinue){
    
    print("choisissez le numero et suivez")
    print("1.Partie classique.Partie rapidement Entre 1 et 100 en 10 coups")
    print("2.Partie rapide.Entre 21 et 42 en 3 coups")
    print("3.Entrer une partie manuellement")
    print("0.Quitter")
    
    let commandNum = readLine()
    
    switch commandNum! {
    case "0":
        isContinue = false
    case "1":
        print("Entre 1 et 100 en 10 coups.Tapez 'q', si vous voulez arrêter")
        //classique()
        gameFixed(1,100,10)
    case "2":
        print("Entre 21 et 42 en 3 coups.Tapez 'q', si vous voulez arrêter")
        gameFixed(21,42,3)
        
    //print("2222222")
    case "3":
        print("Tapez 'q', si vous voulez arrêter")
        gameManual()
        //print("3333333333")
        
    default:
        print("Entrez encore svp")
    }
}




func gameFixed(_ numUpper:UInt32,_ numBound:UInt32,_ timeLimit:Int){
    
    let numRandom = getChiffreRandom(numUpper,numBound)
    let range:CountableClosedRange = Int(numUpper)...Int(numBound)
    var tryTimes = 0
    let timeLimit = timeLimit
    print("Commencez......")
    while(true){

        
        let num = readLine()
        
        //print(num)
        
        if let numInt = Int(num!) {
            
            print("Entrez numero est: \(numInt)")
            
            if(isInRange(numInt,range)){//
                
                if(isCorrect(numInt,numRandom)){
                    break
                    
                }else{
                    //continue
                }
                
            }else{
                //continue
            }
            
            
        }else{
            
            if(num!.uppercased()=="Q"){
                return
            }
            //continue
            
        }
        tryTimes = tryTimes+1
        if(tryTimes>=timeLimit){
            print("\(timeLimit) fois deja ,Redemarrez encore svp")
            break
            
        }
        
        
    }
    
    
    
}

func gameManual(){
    
    var numUpper = 0
    var numBound = 0
    var timeLimit = 0
    
    
    while(true){
        print("Entrez minimum numéro svp")
        let num1 = readLine()
        
        guard let numUpperInput  =  Int(num1!) else{
            print("Mal numero ,Entrez encore svp")
            if(num1!.uppercased()=="Q"){
                return
            }
            continue
            
        }
        
        if(numUpperInput>=0){
            numUpper = numUpperInput
            break
        }else{
            print("le numero doit etre supérieurs à 0,Entrez encore svp")
            continue
        }
    }
    
    
    while(true){
        print("Entrez maximum numéro svp")
        let num2 = readLine()
        
        guard let numBoundInput = Int(num2!) else{
            print("Mal numero ,Entrez encore svp")
            if(num2!.uppercased()=="Q"){
                return
            }
            continue
            
        }
        
        if(numBoundInput >= 0&&numUpper<numBoundInput){
            numBound = numBoundInput
            break
        }else{
            print("le numero doit etre supérieurs au premier,Entrez encore svp")
            continue
        }
        
        
    }
    
    while(true){
        print("Comme bien coups vous voulez?")
        let num3 = readLine()
        guard let timeLimitInput = Int(num3!) else{
            print("Mal numero ,Entrez encore svp")
            if(num3!.uppercased()=="Q"){
                return
            }
            continue
        }
        
        if(timeLimitInput>0){
            timeLimit = timeLimitInput
            break
            
        }else{
            print("le numero doit etre supérieurs à 0,Entrez encore svp")
            continue
        }
    }
    
    gameFixed(UInt32(numUpper),UInt32(numBound), timeLimit)
     
    
    
    
    
    
    
    
}




/*func classique(){
 
 let numRandom = getChiffreRandom(1,100)
 let range:CountableClosedRange = 1...100
 var tryTimes = 0
 let timeLimit = 10
 
 while(true){
 
 if(tryTimes>=timeLimit){
 print("\(timeLimit) fois deja ,Redemarrez encore svp")
 break
 
 }
 
 let num = readLine()
 
 if let numInt = Int(num!) {
 
 tryTimes = tryTimes+1
 
 if(isInRange(numInt,range)){//
 
 if(isCorrect(numInt,numRandom)){
 break
 
 }else{
 continue
 }
 
 }else{
 continue
 }
 
 }else{
 
 continue
 
 }
 }
 
 
 }
 */

//生成随机数
func getChiffreRandom(_ numUpper:UInt32,_ numBound:UInt32)->Int{
    
    
    let chiffreRandom =  Int(arc4random_uniform(numBound - numUpper)+numUpper)//生成区间随机数
    
    //print("Number random \(chiffreRandom)")
    
    return chiffreRandom
    
}



//判断
func isCorrect(_ num:Int,_ numRandom:Int)->Bool{
    var bool = false
    
     
    if num == numRandom {
        print("Gagné")
        bool = true
        
    } else {
        if num < numRandom {
            print("Le nombre à deviner est plus grand.")
        } else {
            print("Le nombre à deviner est plus petit.")
        }
    }
    
    
    
    return bool;
    
}

//是否越界
func isInRange(_ num:Int,_ range:CountableClosedRange<Int>)->Bool{
    
    var bool = false
    
    if range.contains(num) {
        
        bool = true
        
    }else{
        print("Hors limites")
        bool = false
    }
    return bool
}
