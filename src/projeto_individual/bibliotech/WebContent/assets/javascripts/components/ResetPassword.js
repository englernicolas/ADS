export default {
    name: 'ResetPassword',
    data: () => ({
        valid: false,
        content: {
            newPassword: '',
        },
        confirmNewPassword: '',
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        loading: false,
        error: ""
    }),
    methods: {
        validate() {
            this.$refs.form.validate()
        },
        async login() {
            this.sendingEmail = true
            this.responseMessage = ''

            if (this.valid) {
                await axios.post("/auth/recoverPassword", this.userInfo)
                    .then(async (response) => {
                        this.responseMessage = response.data
                    })
                    .catch(error => {
                       this.sendingEmail = false
                       this.responseMessage = "Ocorreu um erro ao recuperar senha!"
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
                    <span class="mx-auto primary--text">{{responseMessage}}</span>
                </v-row>
                <v-row class="mx-10">
                    <v-col>
                        <v-form v-model="valid">
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="content.newPassword" type="password" :rules="requiredMessage" label="Nova senha" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row>   
                            <span class="red--text" v-if="content.newPassword != confirmNewPassword">As senhas não conferem</span> 
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="confirmNewPassword" type="password" :rules="requiredMessage" label="Confirmar nova senha" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row> 
                            <v-row>
                                <v-col>
                                    <v-btn class="text-body-2 font-weight-bold white-text mb-10" block height="50px"
                                        :disabled="!valid && sendingEmail" color="primary" @click="login">
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