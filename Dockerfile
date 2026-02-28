#inventory docker file

# Stage 1: Development/Build
FROM node:20-slim AS build
WORKDIR /home/node/parking-api

RUN apt-get update && apt-get install -y

COPY --chown=node:node package*.json ./

RUN npm ci

COPY --chown=node:node . .

RUN chmod 777 node_modules

RUN chmod 777 /home/node/parking-api/prisma/*

RUN npx prisma generate --schema=/home/node/parking-api/prisma/schema.prisma

RUN npm run build

RUN chmod 777 dist

CMD ["npm", "run", "parking:dev"]

EXPOSE 3000
