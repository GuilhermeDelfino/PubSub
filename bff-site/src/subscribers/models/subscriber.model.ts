/* eslint-disable @typescript-eslint/no-unused-vars */
import { Field, ID, ObjectType } from '@nestjs/graphql';

@ObjectType()
export class Subscriber {
  @Field((type) => ID)
  id: string;
  @Field()
  name: string;
  @Field()
  email: string;
}
