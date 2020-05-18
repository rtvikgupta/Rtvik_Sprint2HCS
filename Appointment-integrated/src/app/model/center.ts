import { Test } from './test';

export class Center {
    id: string;
    name: string;
    tests: Array<Test>;

    constructor(id:string,name:string,tests:Array<Test>) {
        this.id = id;
        this.name = name;
        this.tests = tests;
    }
}