import { Field, InputType } from '@nestjs/graphql';
import { IsNotEmpty, Length, MinDate } from 'class-validator';

@InputType()
export class CreateEventInput {
  @Field()
  @Length(3, 60)
  @IsNotEmpty()
  title: string;

  @Field()
  @IsNotEmpty()
  @Length(3, 255)
  description: string;

  @Field()
  @IsNotEmpty()
  @MinDate(() => new Date())
  date: Date;

  @Field()
  @IsNotEmpty()
  @Length(3, 60)
  location: string;

  @Field()
  @IsNotEmpty()
  @Length(3, 60)
  author: string;
}
