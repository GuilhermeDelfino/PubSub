import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios';
import { EventsResolver } from './resolvers/events.resolver';
import { EventsService } from './services/events.service';
import { MsEventsService } from './services/ms-events/ms-events.service';
import { AuthModule } from 'src/auth/auth.module';

@Module({
  imports: [HttpModule, AuthModule],
  providers: [
    EventsResolver,
    { provide: EventsService, useClass: MsEventsService },
  ],
})
export class EventsModule {}
