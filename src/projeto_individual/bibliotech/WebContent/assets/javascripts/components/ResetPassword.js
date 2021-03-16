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
    computed: {
        tokenId() {
            return this.$route.query.code
        }
    },
    methods: {
        validate() {
            this.$refs.form.validate()
        },
        async resetPassword() {
            this.loading = true
            this.responseMessage = ''

            const {newPassword} = this.content
            const body = {
                newPassword: btoa(newPassword)
            }

            if (this.valid) {
                await axios.post(`/auth/resetPassword?code=${this.tokenId}`, body)
                    .then(async (response) => {
                        this.responseMessage = response.data
                        this.$router.push({ path: '/login'})
                    })
                    .catch(error => {
                       this.loading = false
                       this.responseMessage = "Ocorreu um erro ao resetar senha!"
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
                                        :disabled="!valid && loading" color="primary" @click="resetPassword">
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