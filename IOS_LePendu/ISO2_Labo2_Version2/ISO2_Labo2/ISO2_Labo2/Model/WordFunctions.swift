//
//  File.swift
//  ISO2_Labo2
//
//  Created by Liang Chang (Étudiant) on 2022-05-13.
//

import Foundation

class WordFunctions{
    
    
    func isFitSomeLetter(_ letter:String,_ word:String,_ wordNow: inout String) ->(Bool,String) {
        
        var bool = false
        //var newStr = ""
        var i = 0
        print("word : \(word)")
        for index in word.indices {
            
            //newStr.append(wordNow[index])
//            print(index)
            
            print("word \(index): \(word[index])")
            print("letter: \(letter)")
            if(word[index] == wordNow[index]){
                
            }else if(String(word[index]) == letter){
                
                let startIndex = wordNow.index(wordNow.startIndex, offsetBy:i)
                
                let endIndex = wordNow.index(wordNow.startIndex, offsetBy:i)
                
                let  range = startIndex...endIndex
                wordNow.replaceSubrange(range, with:letter)
                bool = true
                break;
            }
            
            i = i+1
            
        }
        print(wordNow)
        return (bool,wordNow)
    }
    
    func countPoint(_ point:Int,_ times:Int) -> Int{
        
        var result = 0;
        
        if(times==0){
            result = point
        }else if(times<=2){
            result = point-times*1
        }else if(times<=4){
            result = point-times*2
        }else if(times == 5){
            result = point - 3*times
        }else if(times ==  6){
            result = 0
        }
        print("new points is \(result)")
        return  result;
        
        
    }
    
    func getRandomNum(_ length:Int)->Int{
        
        let randomNum = Int(arc4random_uniform(UInt32(length)))
        
        return randomNum
        
    }
    
    func getRandomNums(_ length:Int,_ times:Int)->[Int]{
        
        var numbers:[Int] = []
        var i = 0;
        
        while i < times {
            
            let rn = getRandomNum(length)
            if(!numbers.contains(rn)){
                numbers.insert(rn,at:i)
                i = i+1
            }else{
                print("same,no insert")
            }
            
        }
        print("???????????\(numbers)")
        
        return  numbers
        
    }
    
    func replaceLetterToSymbole(_ word:inout String,_ place:Int,_ symbole:String)->String {
        var i = 0
        
        for index in word.indices {
            
            //newStr.append(wordNow[index])
            
            if(i == place){
                
                let startIndex = word.index(word.startIndex, offsetBy:i)
                
                let endIndex = word.index(word.startIndex, offsetBy:i)
                
                let  range = startIndex...endIndex
                word.replaceSubrange(range, with:symbole)
                
                break
                
            }
            
            i = i+1
        }
        
        return word
    }
    
    
    func getWord()->String{
        var result = ""
//        print("0000000000000")
        let session = URLSession.shared
        let url = URL(string: "https://random-word-api.herokuapp.com/word")!
        let task = session.dataTask(with: url, completionHandler: { data, response, error in
            //如果有错误
            if let error = error{
                //error.localizedDescription
                //todo 对错误进行操作
                print(" \(error.localizedDescription)")
                return
            }
            //如果没有有响应，并且响应返回码不为200
            guard let httpResponse  = response as? HTTPURLResponse,httpResponse.statusCode == 200 else{
                //todo 对错误进行操作
               
                return
                
            }
            //如果没有数据对象
            guard let data1 = data else{
                print("no data")
                
                return
            }
            // Serialize the data into an object
            do {
//                let json = try JSONSerialization.jsonObject(with: data!, options: [])
               result = String(data:data1, encoding: String.Encoding.utf8) ?? "1111"
               
                
            } catch {
                print("Error during JSON serialization: \(error.localizedDescription)")
            }
            
        })
        task.resume()
        
        
        return  result
    }
    
    func getFilm(){
        let url = URL(string:"")!;
        //        let task = URLSession.shared.dataTask(with:url){
        //            (data,response,error) in
        let task = URLSession.shared.dataTask(with: url) { (data, response, error) in
            
            //如果有错误
            if let error = error{
                //error.localizedDescription
                //todo 对错误进行操作
                
                return
            }
            //如果没有有响应，并且响应返回码不为200
            guard let httpResponse  = response as? HTTPURLResponse,httpResponse.statusCode == 200 else{
                //todo 对错误进行操作
                return
                
            }
            //如果没有数据对象
            guard let data = data else{
                print("no data")
                return
            }
            
            //对json对象进行操作
            
            let data1: Data = try! JSONSerialization.data(withJSONObject: data, options: .fragmentsAllowed)
            print("Now we get data /(data)")
            // 将data转成字符串输出
            let str = String(data:data1, encoding: String.Encoding.utf8)
            //print(str as Any)
            
            
            print("长度： /(str)")
            
            
            
        }
        
        task.resume();
    }
    //Todo getCurrentPoint
    //Todo saveUserInfo(_ name:String, point:Int)
    
   
    
    
}
