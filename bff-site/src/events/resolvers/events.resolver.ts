/* eslint-disable @typescript-eslint/no-unused-vars */
import { Args, Mutation, Query, Resolver } from '@nestjs/graphql';
import { Event } from '../models/event.model';
import { CreateEventInput } from '../dtos/inputs/create.event.input';
import { EventsService } from '../services/events.service';
import { UseGuards } from '@nestjs/common';

@Resolver((of) => Event)
export class EventsResolver {
  constructor(private readonly service: EventsService) {}
  @Query((returns) => [Event], { nullable: true, defaultValue: [] })
  async findAllEvents(): Promise<Event[]> {
    return this.service.findAll();
  }
  @Query((returns) => Event, { nullable: true })
  async findOneEvent(
    @Args('id', { nullable: true }) id: string,
  ): Promise<Event | null> {
    return this.service.findOne(id);
  }
  @Mutation((returns) => Event)
  async createEvent(
    @Args('data', { nullable: true })
    data: CreateEventInput,
  ): Promise<Event> {
    return this.service.create({ authorName: data.author, ...data });
  }
}
