//
//  WordFunctionsTestCase.swift
//  ISO2_Labo2UITests
//
//  Created by Liang Chang (Étudiant) on 2022-05-13.
//

import XCTest
@testable import ISO2_Labo2

class WordFunctionsTestCase: XCTestCase {
             
    let wf = WordFunctions();
    let lc = LetterCell();
    
    func testGivenALetter_WhenFit_ThenReturnTrueAndNewResult(){
        var str = "**JJ***"
        wf.isFitSomeLetter("K", "HHJJKKA", &str)
        
    }
    
    let times = 2;
    func testGivenTimes_WhenSameRandom_ThenGetRandomAgain(){
        
        wf.getRandomNums(8,2)

    }
    
    
    
    func testGiven_When_ThenGetWord(){
        let str = wf.getWord()
        print("get word !!! \(str)")

    }
    
    func testHttp(){
        wf.httpRequest("https://www.themealdb.com/api/json/v1/1/random.php");
        
    }
    
    
    

}
