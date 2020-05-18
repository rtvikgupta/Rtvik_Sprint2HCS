export class Createuser {
    userPassword:string;
    userName:string;
    contactNo:string;
    userEmail:string;
    age:number;
    gender:string;

    constructor(userPassword:string,userName:string,contactNo:string,userEmail:string,age:number,gender:string){
        this.userPassword = userPassword;
        this.userName = userName;
        this.contactNo = contactNo;
        this.userEmail = userEmail;
        this.age = age;
        this.gender =gender;
    }
}
