import { Field, InputType } from '@nestjs/graphql';
import { IsEmail, IsNotEmpty, Length } from 'class-validator';

@InputType()
export class CreateSubscriberInput {
  @Field()
  @Length(3, 50)
  @IsNotEmpty()
  name: string;
  @Field()
  @IsNotEmpty()
  @IsEmail()
  email: string;
}
