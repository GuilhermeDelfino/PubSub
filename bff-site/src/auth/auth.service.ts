import { HttpService } from '@nestjs/axios';
import { CACHE_MANAGER } from '@nestjs/cache-manager';
import { Inject, Injectable, Logger } from '@nestjs/common';
import { AxiosResponse } from 'axios';
import { Cache } from 'cache-manager';
import { ConnectionError } from 'src/errors/connection.error';
import { KEY_TOKEN_CACHE, TTL_CACHED_TOKEN } from './contants';

@Injectable()
export class AuthService {
  private URL: string;
  private logger = Logger;

  constructor(
    private readonly http: HttpService,
    @Inject(CACHE_MANAGER) private readonly cache: Cache,
  ) {
    this.URL = process.env.API_AUTH_URL;
  }
  async getToken(): Promise<string> {
    const cachedToken = await this.getCachedToken();
    // if (cachedToken) return cachedToken;

    this.logger.log('Creating JWT Token to connect with Microservices');
    try {
      const requestBody = this.createAuthRequestBody();
      const response = await this.http.axiosRef.post(this.URL, requestBody);
      const token = this.getTokenFromResponse(response);
      this.cacheToken(token);
      return token;
    } catch (error) {
      this.logger.error('Error to create JWT Token', error);
      throw new ConnectionError('Error to create JWT Token');
    }
  }

  private getTokenFromResponse(response: AxiosResponse) {
    this.logger.log(`JWT Token Created`);
    const authorizationHeader = response.headers['authorization'];
    const token = authorizationHeader.replace('Bearer ', '');
    return token;
  }

  private async cacheToken(token) {
    this.logger.debug(`Caching JWT token: '${token}'`);
    await this.cache.set(KEY_TOKEN_CACHE, token, TTL_CACHED_TOKEN);
  }

  private createAuthRequestBody = () => {
    return {
      username: process.env.API_GATEWAY_USERNAME_LOGIN,
      password: process.env.API_GATEWAY_PASSWORD_LOGIN,
    };
  };

  private async getCachedToken(): Promise<string | null> {
    const cachedToken = await this.cache.get(KEY_TOKEN_CACHE);
    if (cachedToken) {
      this.logger.log('Get Cached token');
      return cachedToken as string;
    }
    return null;
  }
}
