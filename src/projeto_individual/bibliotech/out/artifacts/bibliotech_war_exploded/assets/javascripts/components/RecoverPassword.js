export default {
    name: 'RecoverPassword',
    data: () => ({
        valid: false,
        userInfo: {
            email: '',
        },
        emailRules: [
            v => !!v || 'Campo obrigatório',
            v => /.+@.+\..+/.test(v) || 'E-mail precisa ser válido',
        ],
        sendingEmail: false,
        responseMessage: '',
    }),
    methods: {
        validate() {
            this.$refs.form.validate()
        },
        async recoverPassword() {
            this.sendingEmail = true
            this.responseMessage = ''

            if (this.valid) {
                await axios.post("/auth/recoverPassword", this.userInfo)
                    .then(async (response) => {
                        this.responseMessage = response.data
                    })
                    .catch(error => {
                       this.sendingEmail = false
                       this.responseMessage = "Ocorreu um erro ao enviar e-mail!"
                    })
            }
        },
    },
    template: /*html*/ `
        <div class="ma-auto">
            <v-card width="450" class="ma-auto">
                <v-row>
                    <v-col>
                        <v-img src="./assets/images/logo.png" class="mx-auto" height="200" width="200" aspect-ratio="1"></v-img>
                    </v-col>   
                </v-row>
                <v-row v-if="this.responseMessage!=''">                    
                    <span v-if="responseMessage.includes('erro')" class="mx-auto red--text">{{responseMessage}}</span>
                    <span v-else class="mx-auto primary--text">{{responseMessage}}</span>
                </v-row>
                <v-row class="mx-10">
                    <v-col>
                        <v-form v-model="valid">
                            <v-row>
                                <v-col>
                                    <v-text-field v-model="userInfo.email" label="E-mail de recuperação" :rules="emailRules" outlined color="grey"
                                        required></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-btn class="text-body-2 font-weight-bold white-text mb-10" block height="50px"
                                        :disabled="!valid && sendingEmail" color="primary" @click="recoverPassword">
                                        Enviar
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-form>   
                    </v-col>
                </v-row>
            </v-card>
        </div>`
}