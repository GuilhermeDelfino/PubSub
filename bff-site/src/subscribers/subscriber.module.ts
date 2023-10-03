import { Module } from '@nestjs/common';
import { SubscriberResolver } from './resolvers/subscriber.resolver';
import { MsSubscriberService } from './services/ms-subscribers/ms-subscriber.service';
import { SubscriberService } from './services/subscriber.service';
import { HttpModule } from '@nestjs/axios';
import { AuthModule } from 'src/auth/auth.module';

@Module({
  imports: [HttpModule, AuthModule],
  providers: [
    SubscriberResolver,
    { provide: SubscriberService, useClass: MsSubscriberService },
  ],
})
export class SubscriberModule {}
