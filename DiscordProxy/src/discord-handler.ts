import {Client, VoiceSettings} from "discord-rpc";
import {createLoginData, DiscordRpcConfig} from "./discordRpcAuth.js";
import * as rpc from "discord-rpc";

export class DiscordHandler {
    private voiceSettings?: VoiceSettings;
    private client: Client;

    constructor() {
    }

    public async init(config: DiscordRpcConfig): Promise<void> {
        const client: Client = await this.createClient(config);
        this.client = client;

        this.voiceSettings = await client.getVoiceSettings();

        this.setActivity();
    }

    public debug(): void {
        if (!this.checkAvailable())
            return;

        const client: Client = this.client;

        let state = true;
    }

    public toggleMute(): void {
        this.setMuted(!this.voiceSettings.mute);
    }

    public setMuted(mute: boolean): void {
        this.changeVoiceSettings({mute});
    }

    public isUserMuted(): boolean {
        return this.voiceSettings?.mute ?? false;
    }

    public isAvailable(): boolean {
        return !!this.client;
    }

    private checkAvailable(): boolean {
        if (!this.isAvailable()) {
            console.error(`Discord RPC Client not available!`);
            return false;
        }
        return true;
    }

    private changeVoiceSettings(options: Partial<VoiceSettings>) {
        if (!this.voiceSettings) {
            console.error("discord not fully initialised (yet)!");
            return;
        }

        if (!this.checkAvailable())
            return;

        const client: Client = this.client;

        // ignore wrongly typed lib (everything should be optional!)
        client.setVoiceSettings(options as VoiceSettings).then((result: VoiceSettings): void => {
            console.debug(`setVoiceSettings response: ${JSON.stringify(result)}`);

            this.voiceSettings = result;
        });
    }

    private setActivity(): void {
        if (!this.checkAvailable())
            return;

        // eslint-disable-next-line @typescript-eslint/no-non-null-asserted-optional-chain,@typescript-eslint/no-non-null-assertion
        const client: Client = this.client;

        client.setActivity({
            state: "POSE",
            details: "POSE Discord Proxy",
            startTimestamp: Date.now(),
            // largeImageKey: "https://i.imgur.com/n5UkT3g.png",
            // largeImageText: "GM Application Control",
            // smallImageKey: "https://github.com/codeoverflow-org/nodecg-io-docs/blob/main/docs/assets/logo.png?raw=true",
            // smallImageText: "NodeCG-IO"
        }, process.pid).then((result: unknown): void => {
            console.debug(`setActivity result: ${JSON.stringify(result)}`);
        });
    }

    private async createClient(config: DiscordRpcConfig): Promise<Client> {
        const client = new rpc.Client({transport: "ipc"});
        const login = await createLoginData(client, config, ["identify", "rpc"]);
        return client.login(login);
    }
}
