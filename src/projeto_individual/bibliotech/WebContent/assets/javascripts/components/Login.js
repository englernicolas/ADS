export default {
    name: 'Login',
    data: () => ({
        valid: false,
        userInfo: {
            email: '',
            password: ''
        },
        emailRules: [
            v => !!v || 'Campo obrigatório',
            v => /.+@.+\..+/.test(v) || 'E-mail precisa ser válido',
        ]
        ,
        passwordRules: [
            v => !!v || 'Campo obrigatório',
        ],
        loggingIn: false,
        error: '',
    }),
    methods: {
        validate() {
            this.$refs.form.validate()
        },
        async login() {
            this.loggingIn = true
            this.error = ''
            this.userInfo.password = btoa(this.userInfo.password)

            if (this.valid) {
                await axios.post("/auth/login", this.userInfo)
                    .then(async (response) => {
                        auth.setToken(response.data.token)
                        await this.getUserInformations()
                        this.redirect()
                    })
                    .catch(error => {
                        this.loggingIn = false
                       this.error = "Ocorreu um erro ao tentar logar"
                    })
            }
        },

        async getUserInformations() {
            await axios.get("/auth/me")
                .then((response) => {
                    auth.setUser(response.data)
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar os dados do usuário"
                })
        },

        redirect() {
            auth.loggedIn = true
            window.location.reload()
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
                <v-row v-if="this.error!=''">
                    <span class="mx-auto red--text">{{error}}</span>
                </v-row>
                <v-row class="mx-10">
                    <v-col>
                        <v-form v-model="valid">
                            <v-row>
                                <v-col>
                                    <v-text-field v-model="userInfo.email" label="E-mail" :rules="emailRules" outlined color="grey"
                                        required></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field type="password" v-model="userInfo.password" label="Senha" :rules="passwordRules"
                                        outlined color="grey" required></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-btn class="text-body-2 font-weight-bold white-text" block height="50px"
                                        :disabled="!valid && loggingIn" color="primary" @click="login">
                                        Entrar
                                    </v-btn>
                                </v-col>
                            </v-row>
                            <v-row class="mb-10">
                                <v-col class="d-flex justify-center">
                                    <span class="primary--text">Esqueci minha senha</span>
                                </v-col>
                            </v-row>
                        </v-form>   
                    </v-col>
                </v-row>
            </v-card>
        </div>`
}