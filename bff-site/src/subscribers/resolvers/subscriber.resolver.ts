/* eslint-disable @typescript-eslint/no-unused-vars */
import { Args, Mutation, Query, Resolver } from '@nestjs/graphql';
import { Subscriber } from '../models/subscriber.model';
import { SubscriberService } from '../services/subscriber.service';
import { CreateSubscriberInput } from '../dtos/inputs/create.subscriber.input';

@Resolver((of) => Subscriber)
export class SubscriberResolver {
  constructor(private readonly service: SubscriberService) {}
  @Query((returns) => [Subscriber], { nullable: true, defaultValue: [] })
  async findAllSubscribers(): Promise<Subscriber[]> {
    return this.service.findAll();
  }
  @Query((returns) => Subscriber, { nullable: true })
  async findOneSubscriber(@Args('id') id: string): Promise<Subscriber | null> {
    return this.service.findOne(id);
  }
  @Mutation((returns) => Subscriber)
  async createSubscriber(
    @Args('data', { nullable: true })
    data: CreateSubscriberInput,
  ): Promise<Subscriber> {
    const { email, name } = data;
    return this.service.create({ email, name });
  }
}
