export class Createappointment {
    dateTime: String;
    userId: String;
    centerId: String;
    testId: String;

    constructor(dateTime:String,userId:String,centerId:String,testId:String){
        this.dateTime = dateTime;
        this.userId = userId;
        this.centerId = centerId;
        this.testId = testId;
    }
}
