import {DiscordHandler} from "./discord-handler.js";

const client = new DiscordHandler();

import express from 'express';
import bodyParser from 'body-parser';

// Create a new instance of Express
const app = express();

// use middleware to parse incoming requests with JSON payloads
app.use(bodyParser.json());

// Define a route for the home page
app.get('/', (req, res) => {
    res.send('Hello, world!');
});

app.get('/api/proxy/toggle', (req, res) => {
    client.toggleMute();
    res.status(204).send();
});

app.put('/api/proxy/login', async (req, res) => {
    const config = req.body;
    console.log("login with: ", config)
    await client.init(config);
    res.send(config);
});

// Start the server on port 3000
app.listen(3000, () => {
    console.log('Server started on port 3000');
});
