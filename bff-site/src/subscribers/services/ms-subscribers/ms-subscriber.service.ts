import { Injectable, Logger } from '@nestjs/common';
import { SubscriberService } from '../subscriber.service';
import { Subscriber } from '../../models/subscriber.model';
import { HttpService } from '@nestjs/axios';
import { MS_SUBSCRIBERS_QUERIES } from './queries';
import { FindAllResponseMsSubscribers } from '../models/find-all.response';
import { BaseResponseMsSubscribers } from '../models/base.response';
import { SubscriberModelMSSubscriber } from '../models/subscriber.model';
import { ConnectionError } from 'src/errors/connection.error';
import { AuthService } from 'src/auth/auth.service';
import { isStatus2xx } from 'src/functions/http';
const { CREATE_SUBSCRIBER, FIND_ALL, FIND_BY_ID } = MS_SUBSCRIBERS_QUERIES;
@Injectable()
export class MsSubscriberService extends SubscriberService {
  private URL: string;
  private logger = Logger;
  constructor(
    private readonly http: HttpService,
    private readonly auth: AuthService,
  ) {
    super();
    this.URL = process.env.MS_SUBSCRIBER_URL;
  }

  async findAll(): Promise<Subscriber[]> {
    this.logger.log('Finding all subscribers . . .');
    try {
      const res = await this.http.axiosRef.post<FindAllResponseMsSubscribers>(
        this.URL,
        {
          query: FIND_ALL,
        },
        {
          headers: await this.createHeaderWithAuthorization(),
        },
      );
      this.logger.log(res.config);
      this.logger.log(res.request);
      if (res.data.errors) {
        throw new ConnectionError(
          'Should be not able to connect to microservice: MS-Subscribers',
        );
      }
      if (isStatus2xx(res.status)) {
        this.logger.log('All subscribers has been found . . .');
        return res.data.data.findAll.map((item) => ({ ...item } as Subscriber));
      }
      this.logger.log(res.data);
    } catch (error) {
      this.logger.error(`Error finding all subscribers`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Subscribers',
      );
    }
  }
  async findOne(id: string): Promise<Subscriber | null> {
    this.logger.log(`Finding subscriber with id '${id}' . . .`);
    try {
      const res = await this.http.axiosRef.post<
        BaseResponseMsSubscribers<{ findById: SubscriberModelMSSubscriber }>
      >(
        this.URL,
        {
          query: FIND_BY_ID,
          variables: { id },
        },
        {
          headers: await this.createHeaderWithAuthorization(),
        },
      );

      if (res.data.errors) {
        throw new ConnectionError(
          'Should be not able to connect to microservice: MS-Subscribers',
        );
      }
      if (isStatus2xx(res.status)) {
        this.logger.log(`Found subscriber with id '${id}' . . .`);
        return res.data.data.findById as Subscriber;
      }
      this.logger.log(res.data);
    } catch (error) {
      this.logger.error(`Error finding one subscriber`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Subscribers',
      );
    }
  }
  async create(subscriber: {
    name: string;
    email: string;
  }): Promise<Subscriber> {
    this.logger.log(`Creating subscriber . . .`);
    try {
      const res = await this.http.axiosRef.post<
        BaseResponseMsSubscribers<{
          createSubscriber: SubscriberModelMSSubscriber;
        }>
      >(
        this.URL,
        {
          query: CREATE_SUBSCRIBER,
          variables: { subscriber },
        },
        {
          headers: await this.createHeaderWithAuthorization(),
        },
      );

      if (res.data.errors) {
        throw new ConnectionError(
          'Should be not able to connect to microservice: MS-Subscribers',
        );
      }
      if (isStatus2xx(res.status)) {
        const createdSubscriber = res.data.data.createSubscriber as Subscriber;
        this.logger.log(
          `Subscriber has been created! His ID is '${createdSubscriber.id}'`,
        );
        return createdSubscriber;
      }
      this.logger.log(res.data);
    } catch (error) {
      this.logger.error(`Error creating subscriber`, error);
      throw new ConnectionError(
        'Should be not able to connect to microservice: MS-Subscribers',
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
