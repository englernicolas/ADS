import MyAccount from "./components/EditProfile.js"
import Students from "./components/Students.js"
import Librarians from "./components/Librarians.js"
import Books from "./components/Books.js"
import Loans from "./components/Loans.js"
import Reports from "./components/Reports.js"

let routes;
export default routes =  [
    {
        path: '/',
        component: Students
    },
    {
        path: '/profile',
        component: MyAccount
    },
    {
        path: '/students',
        component: Students
    },
    {
        path: '/librarians',
        component: Librarians
    },
    {
        path: '/books',
        component: Books
    },
    {
        path: '/loans',
        component: Loans
    },
    {
        path: '/reports',
        component: Reports
    },
]