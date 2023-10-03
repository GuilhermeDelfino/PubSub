export default class EntityInvalid extends Error{
    constructor(message: string){
        super(message);
    }
}