/* eslint-disable @typescript-eslint/no-unused-vars */
import { Field, ID, ObjectType } from '@nestjs/graphql';

@ObjectType()
export class Event {
  @Field((type) => ID)
  id: string;
  @Field()
  title: string;
  @Field()
  description: string;
  @Field()
  date: Date;
  @Field()
  location: string;
  @Field()
  author: string;
}
