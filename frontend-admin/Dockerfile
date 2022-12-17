# pull official base image
FROM node:19-alpine3.16

# set working directory
WORKDIR /app

# install app dependencies
COPY package.json .
RUN npm install

# add app
COPY . .

# expose port
EXPOSE 3002

# start app
CMD ["npm", "start"]
# RUN npm run build