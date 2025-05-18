package com.example.mapapp.Model

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://zuhgbaxuncdeysupxmfz.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp1aGdiYXh1bmNkZXlzdXB4bWZ6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDcwNzQ4MzMsImV4cCI6MjA2MjY1MDgzM30.2iC6z211uldHWxCS9rloHxxvJYvkr_0RtsnXA-t4lBI"
) {
    install(Postgrest)
    install(Auth)
}
