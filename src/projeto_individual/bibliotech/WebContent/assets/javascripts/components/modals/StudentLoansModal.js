import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'StudentLoansModal',
    data: () => ({
        books: [
            {
                id: 1,
                title: "O casamento",
                author: "Nicholas Sparks",
                gender: "Romance",
                loanDate: "15/03/2002",
                deliveryDate: "15/04/2002",
                debt: '12',
            },
            {
                id: 2,
                title: "O casamento",
                author: "Nicholas Sparks",
                gender: "Romance",
                loanDate: "15/03/2002",
                deliveryDate: "15/04/2002",
            },
        ],
    }),
    template: /*html*/ `
    <div>
        <v-card v-for="book in books" class="mx-6 my-5">
            <v-container>
                <v-row class="mx-3">
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Título:</span>
                        </v-row>
                        <v-row>
                            <span>{{book.title}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Autor:</span>
                        </v-row>
                        <v-row>
                            <span>{{book.author}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Gênero:</span>
                        </v-row>
                        <v-row>
                            <span>{{book.gender}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Data Emp:</span>
                        </v-row>
                        <v-row>
                            <span>{{book.loanDate}}</span>
                        </v-row>
                    </v-col>
                    <v-col>
                        <v-row>
                            <span class="font-weight-bold">Data Ent:</span>
                        </v-row>
                        <v-row>
                            <span>{{book.deliveryDate}}</span>
                        </v-row>
                    </v-col>
                    <v-col v-if="book.debt">
                        <v-row>
                            <span class="font-weight-bold red--text">Multa:</span>
                        </v-row>
                        <v-row>
                            <span class="red--text">R$ {{book.debt}}</span>
                        </v-row>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </div>`
}
