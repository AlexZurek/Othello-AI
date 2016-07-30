#!/usr/bin/env ruby
require 'net/http'

params = { :"-b" => ARGV[1], :"-p" => ARGV[3], :"-t" => ARGV[5] }
uri = URI.parse('http://localhost:8000/othello')
uri.query = URI.encode_www_form(params)

result = Net::HTTP.get_response(uri)
exit(result.body.to_i)
