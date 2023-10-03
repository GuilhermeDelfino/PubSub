import { Injectable, Logger } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { Event } from 'src/events/models/event.model';
import { EventsService } from '../events.service';
import { EventModel } from '../models/event.model';
import { CreateEventDto } from '../models/create-event.dto';
import { ConnectionError } from 'src/errors/connection.error';
import { AuthService } from 'src/auth/auth.service';
import { isStatus2xx } from 'src/functions/http';
import { MsEventMapper } from './mappers/ms-event.mapper';

@Injectable()
export class MsEventsService extends EventsService {
  private URL: string;
  private logger = Logger;

  constructor(
    private readonly http: HttpService,
    private readonly auth: AuthService,
  ) {
    super();
    this.URL = process.env.MS_EVENTS_URL;
  }

  async findAll(): Promise<Event[]> {
    this.logger.log('Finding all Events . . .');
    try {
      const res = await this.http.axiosRef.get<EventModel[]>(this.URL, {
        headers: await this.createHeaderWithAuthorization(),
      });
      if (isStatus2xx(res.status)) {
        this.logger.log('Events has been found!');
        return res.data.map(MsEventMapper.fromResponse);
      }
    } catch (error) {
      this.logger.error(`Error finding events`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Events',
      );
    }
  }
  async findOne(id: string): Promise<Event | null> {
    try {
      this.logger.log(`Finding event with id '${id}' . . .`);
      const res = await this.http.axiosRef.get<EventModel>(
        this.URL + '/' + id,
        {
          headers: await this.createHeaderWithAuthorization(),
        },
      );

      if (isStatus2xx(res.status)) {
        this.logger.log(`Event with id '${id}' has been found`);
        return MsEventMapper.fromResponse(res.data);
      }
    } catch (error) {
      this.logger.error(`Error finding event with id ${id}`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Events',
      );
    }
  }
  async create(input: CreateEventDto): Promise<Event> {
    try {
      this.logger.log(`Creating event . . .`);
      const res = await this.http.axiosRef.post<EventModel>(this.URL, input, {
        headers: await this.createHeaderWithAuthorization(),
      });

      if (isStatus2xx(res.status)) {
        const createdEvent = MsEventMapper.fromResponse(res.data);
        this.logger.log(
          `Event has been created! Your id is ${createdEvent.id} . . .`,
        );
        return createdEvent;
      }
    } catch (error) {
      this.logger.error(`Error creating an event`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Events',
      );
    }
  }

  private async createHeaderWithAuthorization() {
    const jwtToken = await this.auth.getToken();
    return {
      Authorization: `Bearer ${jwtToken}`,
    };
  }
}
