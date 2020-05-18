export class ViewUser {
    userId:string;
    userName:string;
    contactNo:string;
    userEmail:string;
    age:number;
    gender:string;

    constructor(userId:string,userName:string,contactNo:string,userEmail:string,age:number,gender:string) {
        this.userId = userId;
        this.userName = userName;
        this.contactNo = contactNo;
        this.userEmail =userEmail;
        this.age = age;
        this.gender = gender;
    }
}
