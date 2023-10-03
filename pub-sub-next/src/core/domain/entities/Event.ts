type EventParams = {
  id: string;
  title: string;
  description: string;
  author: string;
  date: Date;
  location: string;
};
export class Event {
  private _params: EventParams;
  constructor(params: Required<EventParams>) {
    this._params = params;
  }
  public get id() {
    return this._params.id;
  }
  public get title() {
    return this._params.title;
  }
  public get description() {
    return this._params.description;
  }
  public get author() {
    return this._params.author;
  }
  public get date() {
    return this._params.date;
  }
  public get location() {
    return this._params.location;
  }
}
