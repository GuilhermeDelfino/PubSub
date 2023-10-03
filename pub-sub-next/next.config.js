/** @type {import('next').NextConfig} */
const nextConfig = {
  env: {
    MS_SUBSCRIBER_HOST_URL: process.env.MS_SUBSCRIBER_HOST_URL,
    BFF_HOST_URL: process.env.BFF_HOST_URL,
  },
};

module.exports = nextConfig;
