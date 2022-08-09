//
//  ViewController.swift
//  ISO2_Labo2
//
//  Created by Liang Chang (Étudiant) on 2022-05-13.
//

import UIKit

class ViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource,UICollectionViewDelegateFlowLayout {
    let wf = WordFunctions()
    var word = ""
    var worNow = ""
    var errorTimes = 6
    var points = 0
    let letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","W","V","U","X","Y","Z"]
//    lazy var saveNameVC = UIStoryboard(name:"Main",bundle: nil).instantiateViewController(withIdentifier: "snvc")

    @IBOutlet weak var errorTimesTV: UITextView!
    @IBOutlet weak var pointsTV: UITextView!
    @IBOutlet weak var wordTV: UITextView!
    @IBAction func refresh(_ sender: Any) {
        
        self.updateWord()
        errorTimes = 6
        //give it to textview
        
        
               
        
    }
    
    
    @IBAction func Terminate(_ sender: UIButton) {
        saveNameController() ;
        
    }
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        let letterChoose = letters[indexPath.row]
        print(letterChoose)
        let fitResult = wf.isFitSomeLetter(letterChoose, word, &worNow)
        
         
        
        if(fitResult.0 == true){
            
            
            //finally 
            if(word == worNow){
                updateWord();
                let resultPoint = wf.countPoint(20, 6-errorTimes)
                
                self.points = points + resultPoint
                    self.pointsTV.text = String(self.points)
                //errorTimes = 6
            }else{
  
            }
            
            self.wordTV.text = worNow
            
        }else{
            errorTimes = errorTimes - 1
            
            if(errorTimes<=0){
                sleep(1)
                self.updateWord()
                errorTimes = 6
            }else{
                self.errorTimesTV.text = "\(6-errorTimes)/6"
            }
    }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = (view.frame.width)/4
        return CGSize(width:width,height:width)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 20
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 20
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return letters.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = lettersCollectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! LetterCell
        
        cell.letterCellView.text = letters[indexPath.row]
        
        return cell
    }
    
    
    

    @IBOutlet weak var lettersCollectionView: UICollectionView!
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    
    func updateWord(){
        
 
        let session = URLSession.shared
        let url = URL(string: "https://random-word-api.herokuapp.com/word")!
        let task = session.dataTask(with: url, completionHandler: { [self] data, response, error in
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
 
               let result = String(data:data1, encoding: String.Encoding.utf8) ?? "1111"
               word = String(result.dropFirst(2).prefix(result.count-4))
                word =  word.uppercased()
                print(word)
               
                let replacePlaces = (word.count)<=10 ? wf.getRandomNums(word.count,word.count-3) : wf.getRandomNums(word.count,word.count-5)
                
                worNow = word
                for rp in replacePlaces{
                    
                    worNow = wf.replaceLetterToSymbole(&worNow, rp, "*")
                    
                }
                
                DispatchQueue.main.async {
                    self.errorTimes = 6
                    self.wordTV.text =
                        worNow
                    self.errorTimesTV.text = "0/6"
                }
               
                
               
                
            } catch {
                print("Error during JSON serialization: \(error.localizedDescription)")
            }
            
        })
        task.resume()
        
        
         
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lettersCollectionView.dataSource = self
        lettersCollectionView.delegate = self
        // Do any additional setup after loading the view.
        
        self.updateWord()
        
        //give it to textview
//        wordTV.text = String(result.dropFirst(2).prefix(result.count-4))
    }
    
    func saveNameController() {
            var alertController = UIAlertController(title: "标题", message: "这个是UIAlertController的默认样式", preferredStyle: UIAlertController.Style.alert)
            alertController.addTextField {
                (textField: UITextField!) -> Void in
                textField.placeholder = "请输入"
            }
            var okAction = UIAlertAction(title: "确定", style: UIAlertAction.Style.default) {
                (action: UIAlertAction!) -> Void in
                print("确认按钮点击事件")
            }
            var cancelAction = UIAlertAction(title: "取消", style: UIAlertAction.Style.cancel, handler: nil)
            alertController.addAction(okAction)
            alertController.addAction(cancelAction)
            self.present(alertController, animated: true, completion: nil)
        }


}

